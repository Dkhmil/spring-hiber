<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>

<%--@elvariable id="category" type="khmil.model.Category"--%>

<div>
    <h1>category ${category.categoryName}</h1>
    <c:forEach var="p" items="${category.productList}">
        <h3>Product name:
            <a href="<c:url value="/product?p_id=${p.id}"/>">
                <c:out value="${p.productName}"/>
            </a> ${p.price}
        </h3>
    </c:forEach>
</div>
</body>
</html>