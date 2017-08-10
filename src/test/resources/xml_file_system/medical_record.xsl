<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

<xsl:template match="/">
<html>

   <head>
      <title>Medical Record</title>
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
         <xsl:for-each select="medical_record/patient">
            <tr>
               <td>Patient Name: <xsl:value-of select="@name" /></td>
               <td>
					<xsl:for-each select="record">
						<tr>
							<td>Record #<xsl:value-of select="@r_id" /></td>
						</tr>
						
						<tr>
							<td>Description:</td>
							<td><xsl:value-of select="description" /></td>
						</tr>
						
						<tr>
							<td>Diagnosis:</td>
							<td><xsl:value-of select="diagnosis" /></td>
						</tr>
						
						<tr>
							<td>Treatment:</td>
							<td><xsl:value-of select="treatment" /></td>
						</tr>
						
						<tr>
							<td>Prescription</td>
							<td><xsl:value-of select="prescription" /></td>
						</tr>
					</xsl:for-each>
			   </td>
            </tr>
          </xsl:for-each>
      </table>
    </body>
</html>
</xsl:template>
</xsl:stylesheet>