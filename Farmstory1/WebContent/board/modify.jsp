<%@page import="kr.co.farmstory1.bean.ArticleBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="../_header.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	String seq	 = request.getParameter("seq");
	String group = request.getParameter("group");
	String cate  = request.getParameter("cate");
	String asideFile = "./_aside_"+group+".jsp";
	
	ArticleBean article = (ArticleBean) session.getAttribute("article");
%>
<jsp:include page="<%= asideFile %>">
	<jsp:param value="<%= cate %>" name="cate"/>
</jsp:include>

<section id="board" class="modify">
    <h3></h3>
    <article>
        <form action="/Farmstory1/board/proc/modify.jsp">
        	<input type="hidden" name="seq" value="<%= seq %>" />
        	<input type="hidden" name="group" value="<%= group %>" />
        	<input type="hidden" name="cate" value="<%= cate %>" />
            <table>
                <tr>
                    <td>제목</td>
                    <td><input type="text" name="title" value="<%= article.getTitle() %>" /></td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td>
                        <textarea name="content" ><%= article.getContent() %></textarea>                                
                    </td>
                </tr>
                <tr>
                    <td>첨부</td>
                    <td><input type="file" name="file"/><%= article.getFile() %></td>
                </tr>
            </table>
            <div>
                <a href="./view.jsp?seq=<%=seq %>&group=<%= group %>&cate=<%= cate %>" class="btnCancel">취소</a>
                <input type="submit"  class="btnWrite" value="수정완료">
            </div>
        </form>
    </article>
</section>
        
 <!-- 컨텐츠 내용 끝 -->
            </div>

        </article>
    </section>
</div>

<%@ include file ="../_footer.jsp" %>