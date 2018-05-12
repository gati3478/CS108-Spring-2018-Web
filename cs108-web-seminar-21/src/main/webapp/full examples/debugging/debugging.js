///// DEBUGGING UTILITIES

function printDebug(str) {
	document.title = str;
}

/// alternate printDebug to print to header with id="debugInfo"
function printDebugAlt(str) {
	var debugHeader = document.getElementById("debugInfo");
	if (debugHeader) {
		debugHeader.innerHTML = str;
	}
}

function printDebugCoords(x, y, str) {
	if (str)
		printDebug(str + "(" + x + "," + y + ")");
	else
		printDebug("(" + x + "," + y + ")");
}

// debugFlash -- 
//     flashes background red for 1/2 sec, useful for telling if and when 
//     the computer reaches a particular point in the program
function debugFlash() {
	flashOn();
	setTimeout("flashOff();",500);
}

var origBackColor;

function flashOn() {
	var body = document.getElementsByTagName("BODY")[0];
	if (body.currentStyle) {
		origBackColor = body.currentStyle.backgroundColor;
	} else {
		origBackColor = 
		    document.defaultView.getComputedStyle(body,"").backgroundColor;
	}
	body.style.backgroundColor="red";
}

function flashOff() {
	var body = document.getElementsByTagName("BODY")[0];
	body.style.backgroundColor = origBackColor;
}
