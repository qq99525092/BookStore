<%--
  Created by IntelliJ IDEA.
  User: 林资芙
  Date: 2020/8/3
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--分页条的开始--%>
<div id="page_nav">
    <%--判断， 如果当前页不是第一页才显示首页和上一页的按钮--%>
    <c:if test="${requestScope.page.pageNo >1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%--页码的输出开始--%>
    <c:choose>
        <%--第一种大情况:如果总页码小于等于5 ,则页码输出如下.页码范围是: 1 到 总页码--%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
        </c:when>
        <%--第二种大情况: 页码大于5的情况--%>
        <c:when test="${ requestScope.page.pageTotal > 5 }">
            <c:choose>
                <%--第一种小情况: 当前页码是前面3个,1,2,3.页码范围是:1 到 5 固定--%>
                <c:when test="${ requestScope.page.pageNo <= 3 }">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                </c:when>
                <%--第二种小情况:当前页码是最后3个,8,9,10.页码范围是 : 总页码-4 到总页码--%>
                <c:when test="${ requestScope.page.pageNo > requestScope.page.pageTotal-3 }">
                    <c:set var="begin" value="${ requestScope.page.pageTotal - 4 }"></c:set>
                    <c:set var="end" value="${ requestScope.page.pageTotal }"></c:set>
                </c:when>
                <%--第三种情况: 中间页码 ,4,5,6,7. 页码范围是: 当前页码-2 到 当前页码+2--%>
                <c:otherwise>
                    <c:set var="begin" value="${ requestScope.page.pageNo - 2 }"></c:set>
                    <c:set var="end" value="${ requestScope.page.pageNo + 2 }"></c:set>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${ begin }" end="${ end }" var="i">
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
        <c:if test="${i == requestScope.page.pageNo}">
            【${i}】
        </c:if>
    </c:forEach>
    <%--页码的输出结束--%>

    <%-- 判断，如果当前页不是最后一页才显示末页和下一页的按钮--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" id="searchPage" value="确定"/>
    <script type="text/javascript">
        $(function () {
            //给调到指定页码绑定单击事件
            $("#searchPage").click(function () {
                //1.获取输入框中的页码
                var pageNo = $("#pn_input").val();
                /*    // 获取总页码
                    var  pageTotal =
<%--${requestScope.page.pageTotal };--%>
                    //2.跳转到指定的页码
                    if (pageNo <0 || pageNo >pageTotal){
                        return false;
                    }*/
                location.href = "${basePath}${requestScope.page.url}&pageNo=" + pageNo;
            });

        });

    </script>
</div>
<%--分页条的结束--%>

