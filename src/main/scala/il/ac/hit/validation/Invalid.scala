package il.ac.hit.validation
/**
 *  The Invalid case class represents a failed validation result with a reason for failure.
 *  It implements the ValidationResult trait, always returning false for isValid, and providing the reason for the failure via getReason. */

case class Invalid(reason: String) extends ValidationResult {
  def isValid: Boolean = false
  def getReason: Option[String] = Some(reason)
}
