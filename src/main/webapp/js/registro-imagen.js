let profilePic = document.getElementById("profile-pic");
let inputFile = document.getElementById("img");

inputFile.onchange = function(){
    profilePic.src = URL.createObjectURL(inputFile.files[0]);
}