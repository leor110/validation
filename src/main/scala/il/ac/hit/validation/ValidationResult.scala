package il.ac.hit.validation
/**
 * Represents the result of a validation process.
 * This trait defines the contract for all validation results, including both valid and invalid outcomes.
 * It contains methods to determine whether the validation passed or failed and to retrieve the reason for failure
 * if the validation did not pass.
 * Subclasses such as [[Valid]] and [[Invalid]] extend this trait to provide specific implementations for valid and invalid cases.
 */
trait ValidationResult {
  def isValid: Boolean
  def getReason: Option[String]
}
