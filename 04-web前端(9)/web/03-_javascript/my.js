/**
 * 打印小九九口诀Dao页面上
 */
function print(){
    for(var i=1;i<10;i++){
        for(var j=1;j<=i;j++){
            if(i*j<10){
                document.write(i+"*"+j+"=&nbsp;&nbsp;"+i*j+"&nbsp;&nbsp;&nbsp;&nbsp;");
            }
            else
                document.write(i+"*"+j+"="+i*j+"&nbsp;&nbsp;&nbsp;&nbsp;");
        }
        document.write("<br/>");
    }
}