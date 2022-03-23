<%for(apiClass in templateApiClassList){%>
## ${apiClass.order}. <%if(isNotEmpty(apiClass.desc)){%>${apiClass.desc}<%}else{%>${apiClass.className}<%}%>
<%for(apiMethod in apiClass.apiMethodList){%>
<%if(apiMethod.deprecated){%>
### ${apiClass.order}.${apiMethod.order} ~~${apiMethod.comment!}~~
<%}else{%>
### ${apiClass.order}.${apiMethod.order} ${apiMethod.comment!}
<%}%>
**URL:** ```${apiMethod.url}```

**Type:** ${apiMethod.httpMethod}

<%if(isNotEmpty(apiMethod.author)){%>
**Author:** ${apiMethod.author}
<%}%>

**Content-Type:** ${apiMethod.contentType}

<%if(isNotEmpty(apiMethod.apiNote)){%>
**Description:** ${apiMethod.apiNote}
<%}%>

<%if(isNotEmpty(apiMethod.requestHeaderParams)){%>
**Headers:**

Header|Type|Description|Required
---|---|---|---
<%for(param in apiMethod.requestHeaderParams){%>
${param.name!}|${param.markdownSimpleType!}|${param.comment!}|${param.required!}
<%}%>
<%}%>


<%if(isNotEmpty(apiMethod.requestParams)){%>
**Request-parameters:**

Parameter|Type|Description|Required
---|---|---|---
<%for(param in apiMethod.requestParams){%>
${param.name!}|${param.markdownSimpleType!}|${param.comment!}|${param.required!}
<%}%>
<%}%>

<%if(isNotEmpty(apiMethod.requestBodyParams)){%>
**Request-body:**

Parameter|Type|Description|Required
---|---|---|---
<%for(param in apiMethod.requestBodyParams){%>
${param.name!}|${param.markdownSimpleType!}|${param.comment!}|${param.required!}
<%}%>
<%}%>

<%if(isNotEmpty(apiMethod.requestExample)){%>
**Request-example:**
```
${apiMethod.requestExample}
```
<%}%>

<%if(isNotEmpty(apiMethod.responseBodyParams)){%>
**Response:**

Field|Type|Description
---|---|---
<%for(param in apiMethod.responseBodyParams){%>
${param.name!}|${param.markdownSimpleType!}|${param.comment!}
<%}%>
<%}%>

<%if(isNotEmpty(apiMethod.responseExample)){%>
**Response-example:**
```
${apiMethod.responseExample}
```
<%}%>

<%}%>
<%}%>
