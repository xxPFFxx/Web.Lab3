function isValidR(){
    return true;
}
function getR(){
            return document.getElementById("param-r").value;
}
document.getElementById("zoneCanvas").addEventListener("click", function (e) {
    if(isValidR()) {
        const x = (e.x - document.getElementById("zoneCanvas").getBoundingClientRect().left - 180) / (30);
        const y = (e.y - document.getElementById("zoneCanvas").getBoundingClientRect().top - 180) / (-30);
        const r = getR();
        onPointClicked(x,y,r);
    }
});
