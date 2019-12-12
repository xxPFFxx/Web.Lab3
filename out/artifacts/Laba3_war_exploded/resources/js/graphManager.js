
function drawPoint(x,y,color) {
    var plot_canvas = document.getElementById("zoneCanvas");
    var plot_context = plot_canvas.getContext("2d");
    plot_context.beginPath();
    plot_context.fillStyle = color;
    plot_context.arc(x*180/6 + 180, y*(-180)/6 + 180,3,0,2*3.14);
    plot_context.fill();
    plot_context.closePath();

}

function redraw(){
    var plot_canvas = document.getElementById("zoneCanvas");
    var plot_context = plot_canvas.getContext("2d");
    var R = getR();
    plot_context.clearRect(0, 0, plot_canvas.width, plot_canvas.height);
    if(true) {
        plot_context.strokeStyle = "#000000";

        plot_context.fillStyle = "#000000";


        plot_context.beginPath();
        plot_context.arc(180, 180, R * 150 / 5, Math.PI * 1.5, Math.PI * 2);
        plot_context.lineTo(180, 180);
        plot_context.closePath();

        plot_context.rect(180-(R*15), 180, R * 15, R * 30);
        plot_context.fillStyle = 'blue';
        plot_context.fill();

        plot_context.beginPath();
        plot_context.moveTo(180, 180);
        plot_context.lineTo(180, 180 - R * 75 / 5);
        plot_context.lineTo(180 - R * 75 / 5, 180);
        plot_context.lineTo(180, 180);
        plot_context.closePath();
        plot_context.fillStyle = 'blue';
        plot_context.fill();
    }
    plot_context.beginPath();
//Ox
    plot_context.fillStyle = 'black';
    plot_context.moveTo(0, 180);
    plot_context.lineTo(360, 180); //360, 180
    plot_context.lineTo(350, 170); //290, 140
    plot_context.moveTo(360, 180); //360, 180
    plot_context.lineTo(350, 190); //290, 160
    plot_context.moveTo(360, 180); //360, 180
    //0x markers

    plot_context.moveTo(330, 176); //
    plot_context.lineTo(330, 184); //+5
    plot_context.moveTo(300, 176); //
    plot_context.lineTo(300, 184); //+4
    plot_context.moveTo(270, 176); //
    plot_context.lineTo(270, 184); //+3
    plot_context.moveTo(240, 176); //
    plot_context.lineTo(240, 184); //+2
    plot_context.moveTo(210, 176); //
    plot_context.lineTo(210, 184); //+1
    plot_context.moveTo(150, 176); //
    plot_context.lineTo(150, 184); //-1
    plot_context.moveTo(120, 176); //
    plot_context.lineTo(120, 184); //-2
    plot_context.moveTo(90, 176); //
    plot_context.lineTo(90, 184); //-3
    plot_context.moveTo(60, 176); //
    plot_context.lineTo(60, 184); //-4
    plot_context.moveTo(30, 176); //
    plot_context.lineTo(30, 184); //-5

    //Oy
    plot_context.moveTo(180, 0);
    plot_context.lineTo(170, 10);
    plot_context.moveTo(180, 0);
    plot_context.lineTo(190, 10);
    plot_context.moveTo(180, 0);
    plot_context.lineTo(180, 360);
    plot_context.moveTo(30, 180);

    //0y markers

    plot_context.moveTo(176, 330); //
    plot_context.lineTo(184, 330); //+5
    plot_context.moveTo(176, 300); //
    plot_context.lineTo(184, 300); //+4
    plot_context.moveTo(176, 270); //
    plot_context.lineTo(184, 270); //+3
    plot_context.moveTo(176, 240); //
    plot_context.lineTo(184, 240); //+2
    plot_context.moveTo(176, 210); //
    plot_context.lineTo(184, 210); //+1
    plot_context.moveTo(176, 150); //
    plot_context.lineTo(184, 150); //-1
    plot_context.moveTo(176, 120); //
    plot_context.lineTo(184, 120); //-2
    plot_context.moveTo(176, 90); //
    plot_context.lineTo(184, 90); //-3
    plot_context.moveTo(176, 60); //
    plot_context.lineTo(184, 60); //-4
    plot_context.moveTo(176, 30); //
    plot_context.lineTo(184, 30); //-5

    plot_context.closePath();
    plot_context.stroke();
    plot_context.fillStyle = "#000000";
    plot_context.textAlign ="center";
    plot_context.font = "18px Arial";

    plot_context.fillText("x", 345, 165);
    plot_context.fillText("y", 190, 15);
    plot_context.font = "10px Arial";
    plot_context.fillText("0", 170, 195);
    //x
    plot_context.fillText("-5", 30, 195);
    plot_context.fillText("-4", 60, 195);
    plot_context.fillText("-3", 90, 195);
    plot_context.fillText("-2", 120, 195);
    plot_context.fillText("-1", 150, 195);
    plot_context.fillText("1", 210, 195);
    plot_context.fillText("2", 240, 195);
    plot_context.fillText("3", 270, 195);
    plot_context.fillText("4", 300, 195);
    plot_context.fillText("5", 330, 195);
    //y
    plot_context.fillText("5", 170, 35);
    plot_context.fillText("4", 170, 65);
    plot_context.fillText("3", 170, 95);
    plot_context.fillText("2", 170, 125);
    plot_context.fillText("1", 170, 155);
    plot_context.fillText("-1", 170, 215);
    plot_context.fillText("-2", 170, 245);
    plot_context.fillText("-3", 170, 275);
    plot_context.fillText("-4", 170, 305);
    plot_context.fillText("-5", 170, 335);
    callRedraw([{name:'pR', value: R}]);
}

function onPointClicked(x, y, r){

    //sendClick([{name:'pX', value: x},{name:'pY', value: y},{name:'pR', value: r}]);
    sendClick([{name:'pX', value: x},{name:'pY', value: y},{name:'pR', value: r}]);
}