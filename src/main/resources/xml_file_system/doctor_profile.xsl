<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

<xsl:template match="/">
<html>

   <head>
      <title>Doctor Profile</title>
      <style type="text/css">
        table {
            font-family: verdana;
        }

          tr {
              height: 30px;
          }

          td.col1 {
              width: 100px;
          }

          td.col2 {
              width: 400px;
          }

          td.title {
              background: #efe7d9 ;
              border-bottom: 1px solid #336699;
              font: 16 verdana;
              padding: 0 0 0 15px;
          }

          .red {
              color: #ff0000;
          }

          .green {
              color: #249821;
          }

          td.greenRow
          {
              background: #d6efd6;
              border-bottom: 1px solid #437841;

          }
      </style>
   </head>

   <body>
      <table>
         <xsl:for-each select="profile/doctor">
             <!-- if artist is assigned enya, set bg to green. -->

            <tr>
               <td>Doctor Name:</td>
               <td><xsl:value-of select="name" /></td>
            </tr>
            <tr>
               <td>Department:</td>
               <td><xsl:value-of select="department" /></td>
            </tr>
         </xsl:for-each>
      </table>
    </body>
</html>
</xsl:template>
</xsl:stylesheet>