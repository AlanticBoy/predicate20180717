/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2018-06-07 00:35:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsps;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fileupload_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"multipart/form-data; charset=utf-8\"/>\r\n");
      out.write("    <script src=\"../js/jquery-1.7.1.min.js\"></script>\r\n");
      out.write("    <script src=\"http://malsup.github.com/jquery.form.js\"></script>\r\n");
      out.write("    <title>Title</title>\r\n");
      out.write("    <script>\r\n");
      out.write("        /*文件校验*/\r\n");
      out.write("        function validation() {\r\n");
      out.write("            var file = \"\";\r\n");
      out.write("            var suffix = \"\";\r\n");
      out.write("            file = document.getElementById(\"excel\").value;\r\n");
      out.write("\r\n");
      out.write("            if (file != null && file != \"\")\r\n");
      out.write("                suffix = file.substring(file.length - 4);\r\n");
      out.write("\r\n");
      out.write("            if (suffix == \"\" || suffix == null) {\r\n");
      out.write("                alert(\"请选择上传文件！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }\r\n");
      out.write("            return true;\r\n");
      out.write("        }\r\n");
      out.write("    </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div align=\"center\" style=\"margin-top: 10%;\">\r\n");
      out.write("    <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user/importMedicalInfo.action\" method=\"post\"\r\n");
      out.write("          onsubmit=\"validation()\" enctype=\"multipart/form-data\">\r\n");
      out.write("        <div align=\"center\" style=\"margin-top: 10%;\">\r\n");
      out.write("            <input type=\"file\" name=\"file\" id=\"excel\"/><br/>\r\n");
      out.write("            <input type=\"submit\" class=\"btn btn-primary\" value=\"导入\"/>\r\n");
      out.write("        </div>\r\n");
      out.write("    </form>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}