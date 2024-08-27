let tbody = document.getElementById("tBody");

// fetch("http://localhost:3000/user").then((res) => console.log(res));

// fetch("http://localhost:3000/user")
//   .then((res) => res.json())
//   .then((myJson) => console.log(myJson));

fetch("http://localhost:3000/user")
  .then((res) => res.json())
  .then((myJson) => {
    myJson.map((data) => {
      console.log(data);
      tbody.append(
        tr_function(
          data.name,
          data.profilePic,
          data.email,
          data.status,
          data.role,
          data.id
        )
      );
    });
  });

function tr_function(name, profilePic, email, status, role, id) {
  let tr = document.createElement("tr");
  tr.setAttribute("data-id", id);

  tr.innerHTML = `<td class="tableData">
    <div class="items">
      <div class="">
        <img
          src="${profilePic}"
          alt="avatar"
          class="imgStyle"
        />
      </div>
    <div>
    <div class="emplyeeTitle">${name}</div>
    <div class="emplyeeEmail">${email}</div>
    </div>
    </div>
  </td>
  <td class="tableData">
    <span class="statusStyle">${status}</span>
  </td>
  <td class="tableData">
    <span class="roleStyle">${role}</span>
  </td>
  <td class="tableData">
    <span class="deleteIcon">X</span>
    </td>`;
  return tr;
}

let allButton = document.getElementById("allButton");
let activeButton = document.getElementById("activeButton");
let inactiveButton = document.getElementById("inactiveButton");
let memberButton = document.getElementById("memberButton");
let adminButton = document.getElementById("adminButton");

allButton.addEventListener("click", () => {
  filterData("", "");
});

activeButton.addEventListener("click", () => {
  filterData("status", "Active");
});

inactiveButton.addEventListener("click", () => {
  filterData("status", "Inactive");
});

memberButton.addEventListener("click", () => {
  filterData("role", "Member");
});

adminButton.addEventListener("click", () => {
  filterData("role", "Admin");
});
function filterData(filterProperty, filterValue) {
  tbody.innerHTML = "";
  fetch(`http://localhost:3000/user?${filterProperty}=${filterValue}`)
    .then((res) => res.json())
    .then((myJson) => {
      myJson.map((data) => {
        console.log(data);
        tbody.append(
          tr_function(
            data.name,
            data.profilePic,
            data.email,
            data.status,
            data.role
          )
        );
      });
    });
}

let createUserButton = document.getElementById("createUserButton");
let createUserForm = document.getElementById("createUserFrom");

createUserButton.addEventListener("click", () => {
  createUserForm.style.display = "block";
});

let createButton = document.getElementById("createButton");

createButton.addEventListener("click", () => {
  let id = document.getElementById("idInput").value;
  let name = document.getElementById("nameInput").value;
  let email = document.getElementById("emailInput").value;
  let profilePicURL = document.getElementById("profilePicInput").value;
  let status = document.getElementById("statusInput").value;
  let role = document.getElementById("roleInput").value;

  fetch("http://localhost:3000/user", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      id: id,
      name: name,
      email: email,
      profilePic: profilePicURL,
      status: status,
      role: role,
    }),
  })
    .then((res) => res.json())
    .then((data) => {
      console.log(data);
      createUserForm.style.display = "none";
      filterData("", "");
    })
    .catch((err) => console.log(err));
});

let abbadonCreateUserButton = document.getElementById(
  "abbandonCreateUserButton"
);

abbadonCreateUserButton.addEventListener("click", () => {
  createUserFrom.style.display = "none";
  for (const input of createUserForm.querySelectorAll("input")) {
    input.value = "";
  }
});

tbody.addEventListener("click", (event) => {
  if (event.target.classList.contains("deleteIcon")) {
    const tr = event.target.parentNode.parentNode;
    const userId = tr.getAttribute("data-id");
    deleteUser(userId, tr);
  }
});

function deleteUser(userId, tr) {
  fetch(`http://localhost:3000/user/${userId}`, { method: "DELETE", }).then((response) => {
    if (response.ok) {
        console.log(`User with ID ${userId} deleted successfully.`);
        tr.remove();
    }else{
        console.error(`Failed to delete user with ID ${userId}.`);
    }
  }).catch((error) =>{
    console.error(`Error deleting user: ${error}`);
  });
}
