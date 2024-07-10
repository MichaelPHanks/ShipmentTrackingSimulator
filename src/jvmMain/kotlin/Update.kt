interface Update {
    fun performUpdate(arguments: List<String>)
    fun validator(arguments: List<String>): Boolean
}