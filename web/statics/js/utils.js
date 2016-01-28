function accept(id) {
    if (confirm("Are you sure want to delete this direction id=" + id + "?") == true) {
        window.location = '/removeDirection?id='+id;
    } else {
        return;
    }
}

function logout(){
    window.location = 'jsp/welcome.jsp';
}