$(function() {
        $("#search").click(function () {
            var text = $("#search_txt").val();
            if (text.length<2) {
                alert("最少输入两位词语")
            } else {
                $(".search-form").submit();
            }
        })
}
)