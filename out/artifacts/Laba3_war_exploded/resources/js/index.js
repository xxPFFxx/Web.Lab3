function ShowTime() {
    document.getElementById("time").textContent=new Date().toLocaleString();

}
setInterval(() => {
    ShowTime();
}, 6000);

