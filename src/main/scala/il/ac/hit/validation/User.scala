package il.ac.hit.validation
/**
 * Represents a user with basic information including username, email, password, and age.
 * @constructor Creates a new User with the specified username, email, password, and age.
 * @param username The username of the user.
 * @param email The email address of the user.
 * @param password The password for the user.
 * @param age The age of the user.
 */
case class User(username: String, email: String, password: String, age: Int)