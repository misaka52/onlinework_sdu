<script>
    function handleJumpPage(event) {
        var t = event.target.value;
        console.log(t)
        if(isNaN(t) || t < 1 || t > ${page.totalPage})
            return;
        toPage(t);
    }
    function toPage(page) {
        var start;
        if(page <= 1)
            start = 0;
        else
            start = (page-1) * ${page.count};
        window.location.href="?start=" + start;
    }
</script>
<div class="form-inline" style="margin:0px 50px 0px 50px;">
    <ul class="pager">
        <li class="previous <c:if test='${!page.hasPreviouse}'>disabled</c:if>">
            <a onclick="toPage(${page.currentPage}-1)">Previous</a>
        </li>
        <li>
            <input onkeypress="handleJumpPage(event)" type="text" size="3" placeholder="${page.currentPage}" class="form-control input-sm" />
            /
            ${page.totalPage}
        </li>

        <li class="next <c:if test='${!page.hasNext}'>disabled</c:if>">
            <a onclick="toPage(${page.currentPage}+1)">Next</a>
        </li>
    </ul>
</div>