<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes" />
	<xsl:template match="site">
		<website>
			<xsl:attribute name="id">
							<xsl:value-of select="@id" />
						</xsl:attribute>
			<xsl:attribute name="name">
							<xsl:value-of select="@name" />
						</xsl:attribute>
			<description>Lorem Ipsum</description>

			<xsl:apply-templates select="views/view" />
		</website>
	</xsl:template>
	<xsl:template match="views/view">
		<page>
			<xsl:attribute name="id">
								<xsl:value-of select="@id" />
							</xsl:attribute>
			<xsl:attribute name="name">
								<xsl:value-of select="@name" />
							</xsl:attribute>
			<description>
				<xsl:value-of select="description" />
			</description>
			<xsl:apply-templates select="component" />
		</page>
	</xsl:template>

	<xsl:template match="component">
		<widget>
			<xsl:attribute name="id"><xsl:value-of select="@ref" /></xsl:attribute>
			<xsl:apply-templates select="//components/component[@id=current()/@ref]" />
		</widget>
	</xsl:template>
	
	<xsl:template match = "//components/component">
	<xsl:attribute name="type"><xsl:value-of select="@type" /></xsl:attribute>
		<xsl:choose>
			<xsl:when test = "@type = 'TEXT'">
				<text>
					<xsl:value-of select="text" />
				</text>
			</xsl:when>
			<xsl:when test = "@type = 'IMG'">
				<xsl:attribute name="src">
								<xsl:value-of select="@src" />
							</xsl:attribute>
			</xsl:when>
			<xsl:when test = "@type = 'YOUTUBE'">
				<xsl:attribute name="url">
								<xsl:value-of select="@url" />
							</xsl:attribute>
			</xsl:when>
			<xsl:when test = "@type = 'BUTTON'">
				<xsl:attribute name="label">
								<xsl:value-of select="@label" />
							</xsl:attribute>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>