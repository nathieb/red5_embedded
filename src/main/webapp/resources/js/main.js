/* 
 * @version 0.1
 * @author Olivier Thiébaut
 */
function handleComplete(xhr, status, args) {
    var isValid = args.isValid;
    var reponse = args.reponse;
    console.log(reponse);
    console.log(isValid);
    if (isValid) dlm.hide();
}

function activerIndex(e,index)
{
     var composant = document.getElementById(e+":"+index);
     composant.select(index);
}