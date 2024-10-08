package il.ac.hit.validation
/**
 * Trait for validating user data based on various conditions.
 * The `UserValidation` trait extends the function `User => ValidationResult`,
 * meaning it takes a `User` object as input and returns a `ValidationResult`
 * (either `Valid` or `Invalid`). This trait provides combinators (`and`, `or`)
 * that allow multiple validations to be combined together.
 */

trait UserValidation extends (User => ValidationResult) {
  def and(that: UserValidation): UserValidation = { user => //This method will be used to check whether two different conditions are fulfilled.
  (this.apply(user), that.apply(user)) match {
      case (Valid(), Valid()) => Valid()
      case (Invalid(reason1), Invalid(reason2)) => Invalid(reason1 + "; " + reason2)
      case (_, invalid: Invalid) => invalid
      case (invalid: Invalid, _) => invalid
    }
  }

  def or(that: UserValidation): UserValidation = { user => //This method will be used to check whether at least one of two different conditions is fulfilled.
  this.apply(user) match {
      case Valid() => Valid()
      case _ => that.apply(user)
    }
  }
}

object UserValidation {

  def all(validations: UserValidation*): UserValidation = { user =>  //This method will be used to check whether all supplied conditions are fulfilled.
  validations.map(_.apply(user)).find(!_.isValid).getOrElse(Valid())
  }

  def none(validations: UserValidation*): UserValidation = { user =>  //This method will be used to check whether none of the supplied conditions is fulfilled.
    validations.map(_.apply(user)).find(_.isValid).map(_ => Invalid("At least one validation passed")).getOrElse(Valid())
  }

  def emailEndsWithIL: UserValidation = { user => //This method will be used to check whether the email address ends with “il”
  if (user.email.endsWith(".il")) Valid()
    else Invalid("Email doesn't end with .il")
  }

  def emailLengthBiggerThan10: UserValidation = { user => //This method will be used to verify the length of the email address is bigger than 10
  if (user.email.length > 10) Valid()
    else Invalid("Email length is not greater than 10")
  }

  def passwordLengthBiggerThan8: UserValidation = { user => //This method will be used to verify the length of the password is bigger than 8
  if (user.password.length > 8) Valid()
    else Invalid("Password length is not greater than 8")
  }

  def passwordIncludesLettersNumbersOnly: UserValidation = { user => //This method will be used to verify the password includes letters and numbers only
  if (user.password.matches("[A-Za-z0-9]+")) Valid()
    else Invalid("Password contains characters other than letters and numbers")
  }

  def passwordIncludesDollarSign: UserValidation = { user => //This method will be used to verify the password includes the $ character
  if (user.password.contains("$")) Valid()
    else Invalid("Password doesn't contain $")
  }

  def passwordIsDifferentFromUsername: UserValidation = { user => //This method will be used to verify the password is different from the username
  if (user.password != user.username) Valid()
    else Invalid("Password is the same as username")
  }

  def ageBiggerThan18: UserValidation = { user => //This method will be used to verify the age is bigger than 18
  if (user.age > 18) Valid()
    else Invalid("Age is not greater than 18")
  }

  def usernameLengthBiggerThan8: UserValidation = { user => //This method will be used to verify the length of the username is bigger than 8
  if (user.username.length > 8) Valid()
    else Invalid("Username length is not greater than 8")
  }
}
