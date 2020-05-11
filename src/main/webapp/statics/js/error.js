$(function() {
    $(document).on("onload","img",function(){
        console.log("afd")
        console.log(this.complete)
        if (this.complete) {
            this.src = '/outside_war_exploded/statics/img/error.png';
        }
    })

})