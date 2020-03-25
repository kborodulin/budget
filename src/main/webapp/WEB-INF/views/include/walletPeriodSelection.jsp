<%@ page contentType="text/html;charset=UTF-8" %>
<div class="input-group">
    <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon1">Период</span>
    </div>
    <select class="custom-select form-control" name="range" id="period" aria-describedby="basic-addon1" action="/transactions" method="get">
        <option value="1" ${periodselected == 1 ? "selected" : ""}>день</option>
        <option value="2" ${periodselected == 2 ? "selected" : ""}>неделя</option>
        <option value="3" ${periodselected == 3 ? "selected" : ""}>месяц</option>
        <option value="4" ${periodselected == 4 ? "selected" : ""}>год</option>
        <option value="5" ${periodselected == 5 ? "selected" : ""}>весь</option>
    </select>
</div>
<script>
    document.querySelector("#period").addEventListener("change", function(){
        //this.form.submit();
        this.disabled = true;
        window.location = window.location.origin + `/transactions?range=` + this.value;
    });
</script>