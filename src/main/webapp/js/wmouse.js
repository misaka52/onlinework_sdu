// JavaScript Document

function  personalbtn() {
    var btn = document.getElementById('btn1');
    btn.onmouseover = function() {
        this.style.backgroundColor = '#ec6941';
    }
    btn.onmouseout = function() {
        this.style.backgroundColor ='#c67553';
    }
}

function  functionbtn() {
    var btn = document.getElementById('btn2');
    btn.onmouseover = function() {
        this.style.backgroundColor = '#ec6941';
    }
    btn.onmouseout = function() {
        this.style.backgroundColor = '#c67553';
		
    }
}

   
window.onload=function(){
	personalbtn();
	functionbtn();

}