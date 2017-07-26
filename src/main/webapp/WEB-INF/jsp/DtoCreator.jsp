<%--
  Created by IntelliJ IDEA.
  User: LZ
  Date: 2017/6/20
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>DTOCreater</title>
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<!-- 遮罩层DIV -->
<div style="width: 100%;position: relative; background: #dfdfdf; background-color: #dfdfdf;">
<%--<div  id="overlay" style="text-align: center;margin-top: 20px; background-color: #000; opacity:0.3;z-index: 2" >
    <textarea style="border:solid; border-color: #ffffff;"> sdfsdfsdfsdfsdf</textarea>
</div>--%>
    <div style="text-align: center;margin-top: 20px; width:40%; float: left">
        <div style="text-align: center;margin-top: 20px;">
            <font size="6" style="text-align: center">DTO Creator V1.0</font>
            </div>
            <div style="text-align: center;margin-top: 20px">
            <form id="createrForm" method="post">
                <select id="environment" style="margin-bottom: 12px">
                    <option value="api">Api</option>
                    <option value="common">Common</option>
                </select></br>
                <div style="display:inline;margin-top: 15px">
                <input type="button" value="Show DTO" onclick="showCode(1)"/>
                <input type="button" value="Show Service" onclick="showCode(2)"/>
                <input type="button" value="Show Controller" onclick="showCode(3)"/>
                <input type="button" value="Create All Source" onclick="createAction(4)"/>
                </div>
                <table align="center" style="margin-top: 30px">
                    <tr>
                        <th><u>PackageName</u>：</th>
                        <th><input type="text" id="packageName" placeholder="如：org.openmore"/></th>
                    </tr>
                    <tr>
                        <th><u>DTO</u>：</th>
                        <th><input type="text" id="className" placeholder="如：User"/></th>
                    </tr>
                    <tr>
                        <th><u>DTO中文名</u>：</th>
                        <th><input type="text" id="className_zn" placeholder="如：用户"/></th>
                    </tr>
                    <tr>
                        <th><u>Controller说明</u>：</th>
                        <th><input type="text" id="controller_desc" placeholder="如：用户控制器"/></th>
                    </tr>
                </table>

                <div align="center" style="margin-top: 30px"><font size="4" >DTO属性：</font></div>

                <table align="center" style="margin-top: 30px" id="attrs">
                </table>
            </form>
                <button  id="addButton" data-number="0" onclick="addProperties()" style="border: none; background-image: url('http://pic2.16pic.com/00/21/82/16pic_2182030_b.jpg'); width:35px;height:35px; background-size: 100%; margin-top: 15px"/>
            </div>
    </div>

    <div  style="background-color: #ffffff; width:60%;height: 100%;  float: right" >
        <textarea id="overlay" style="padding: 8px; color: #FFFFFF; margin-top: 50px; border:solid; background-color:#000000; border-color: #000000; width: 100%; height: 90%" disabled="disabled"> Source Code</textarea>
    </div>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/DtoCreator.js"></script>
</body>
</html>
