const todoInput = document.querySelector(".todo-input");
const todoButton = document.querySelector(".todo-button");
const todoList = document.querySelector(".todo-list");

todoButton.addEventListener("click",addTodo);

function addTodo(event){
    event.preventDefault();
    const todoDiv = document.createElement("div");
    todoDiv.classList.add("todo");
    const newTodo = document.createElement("li");
    newTodo.innerText = todoInput.value;
    newTodo.classList.add("todo-item");
    todoDiv.appendChild(newTodo);
    const completeButton = document.createElement("button");
    completeButton.innerHTML = "<a>&#10004;</a>";
    completeButton.classList.add("complete-btn");
    todoDiv.appendChild(completeButton);
    const trashButton = document.createElement("button");
    trashButton.innerHTML = "<a>&#9249;</a>";
    trashButton.classList.add("trash-btn");
    todoDiv.appendChild(trashButton);
    todoList.appendChild(todoDiv);
}

todoList.addEventListener("click",deleteOrCheck);

function deleteOrCheck(e){
    console.log(event.target);
    const item = e.target;
    if(item.classList[0] === "trash-btn"){
        const todo = item.parentElement;
        todo.classList.add("fall");
        todo.addEventListener('transitioned',function(){todo.remove();});
    }
    if(item.classList[0] === "complete-btn"){
        const todo = item.parentElement;
        todo.classList.toggle('completed');
    }
}

