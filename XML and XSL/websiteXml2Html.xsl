<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method = "html" />
	<xsl:template match="/">
		<h1>Website</h1>
		<br></br>
		<h2>Pages</h2>
		<br></br>
		<xsl:for-each select="website/page">
			<xsl:value-of select = "@name" />, <xsl:value-of select="description"></xsl:value-of>
			<br></br>
		</xsl:for-each>
		
		<h2>Widget</h2>
		<TABLE border = "1">
			<THEAD>
				<TR>
					<TH>ID</TH>
					<TH>TYPE</TH>
					<TH>TEXT</TH>
					<TH>SRC</TH>
					<TH>URL</TH>
					<TH>LABEL</TH>
				</TR>
			</THEAD>
 		<TBODY>
 			<xsl:apply-templates select = "website/page/widget" />
		</TBODY>
	</TABLE>	
  </xsl:template>
  <xsl:template match = "website/page/widget">
		<TR>
			<TD><xsl:value-of select = "@id" /></TD>
			<TD><xsl:value-of select = "@type" /></TD>
			<TD><xsl:value-of select = "text" /></TD>
			<TD><xsl:value-of select = "@src" /></TD>
			<TD><xsl:value-of select = "@url" /></TD>
			<TD><xsl:value-of select = "@label" /></TD>
		</TR>
	</xsl:template>	
 </xsl:stylesheet>