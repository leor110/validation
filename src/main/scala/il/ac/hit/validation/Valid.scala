package il.ac.hit.validation
/**
 * Represents a successful validation result.
 * This case class is used to indicate that a validation has passed. It extends
 * the `ValidationResult` trait and always returns `true` for the `isValid` method,
 * meaning the validation was successful. The `getReason` method returns `None`
 * as there is no failure reason for valid results.
 */
case class Valid() extends ValidationResult {
  def isValid: Boolean = true
  def getReason: Option[String] = None
}
