var setAccount = document.querySelectorAll(".set-control");


for (let input of setAccount)
{
    input.addEventListener("input",()=>{
        document.querySelector("#save-item-account").style.display="";
    })
}
