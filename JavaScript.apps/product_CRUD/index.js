var selectedRow = null;

function onFormSubmit(event) {
    event.preventDefault();
    var formData = readFormData();
    if(selectedRow === null){
        console.log("apelez insert func!");
        insertNewRecord(formData);
    }
    else{
        updateRecord(formData);
        selectedRow = null;
    }
    resetForm();
}

function readFormData(){
    var formData = {};
    formData["productCode"] = document.getElementById("productCode").value;
    formData["product"] = document.getElementById("product").value;
    formData["quantity"] = document.getElementById("quantity").value;
    formData["price"] = document.getElementById("price").value;
    console.log("luat date din form!");
    return formData; 
    
}


function insertNewRecord(data){
    var table = document.getElementById("StoreList").getElementsByTagName("tbody")[0];
    var newRow = table.insertRow(table.length);
    var cell1 = newRow.insertCell(0);
        cell1.innerHTML = data.productCode;
    var cell2 = newRow.insertCell(1);
        cell2.innerHTML = data.product;
    var cell3 = newRow.insertCell(2);
        cell3.innerHTML = data.quantity;
    var cell4 = newRow.insertCell(3);
        cell4.innerHTML = data.price;
    var cell5 = newRow.insertCell(4);
        cell5.innerHTML = `<button onClick="onEdit(this)">Edit</button><button onClick="onDelete(this)">Delete</button>`;
    selectedRow = null;
    console.log("inserat!");
}


function onEdit(button){
    selectedRow = button.parentElement.parentElement;
    document.getElementById("productCode").value = selectedRow.cells[0].innerHTML;
    document.getElementById("product").value = selectedRow.cells[1].innerHTML;
    document.getElementById("quantity").value = selectedRow.cells[2].innerHTML;
    document.getElementById("price").value = selectedRow.cells[3].innerHTML;
}

function updateRecord(formData){
    selectedRow.cells[0].innerHTML = formData.productCode;
    selectedRow.cells[1].innerHTML = formData.product;
    selectedRow.cells[2].innerHTML = formData.quantity;
    selectedRow.cells[3].innerHTML = formData.price;
}

function onDelete(button){
    if (confirm("do you want to delete this record?")){
        row = button.parentElement.parentElement;
        document.getElementById("StoreList").deleteRow(row.rowIndex);
    }
    resetForm();
}

function resetForm(){
    document.getElementById("productCode").value = '';
    document.getElementById("product").value = '';
    document.getElementById("quantity").value = '';
    document.getElementById("price").value = '';
}
