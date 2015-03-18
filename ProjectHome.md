# proctosequel : #
_proctosequel_ aim to help developer to create big SQL in procedural way and execute them as single SQL for performance purpose.

  * Handle subqueries, queries join,query dependency.

  * Export useful small queries to stored functions.

  * Possibly, handle synchronization of deliberate data redundancy.

  * Some useful query parsing api, like get query count(...) .

  * Create java interfaces for main queries.

It will be useful essentially for small BI apps.

_proctosequel_ **is not** a project :

  * of query simplification. simplification will possibly be performed to simplify internal transformation (multiple cursors of same table). however, end user will not see his query simplified.

  * of syntax and semantics check. Developer must push SQL free of syntax and semantics errors. saying that, exception handling will be at its minimum for first releases. However, internal transformation of _proctosequel_ shall be free of syntax and semantics errors.

some limitations :

  * some of superfluous or very complex sql fragments may generate errors (ex : parenthesis in join expression )
  * exception handling will be at its minimum for first releases.