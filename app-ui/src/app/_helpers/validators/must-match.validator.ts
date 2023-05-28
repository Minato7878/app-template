import { FormGroup, ValidationErrors } from '@angular/forms';

export function MustMatch(controlName: string, matchingControlName: string) {
  return (formGroup: FormGroup): ValidationErrors | null => {
    const control = formGroup.controls[controlName];
    const matchingControl = formGroup.controls[matchingControlName];

    if (!control || !matchingControl) {
      // Return null if controls haven't been initialized yet
      return null;
    }

    if (matchingControl.errors && matchingControl.errors['mustMatch']) {
      // Return null if another validator has already found an error on the matchingControl
      return null;
    }

    if (control.value !== matchingControl.value) {
      // Set error on matchingControl if validation fails
      matchingControl.setErrors({ mustMatch: true });
    } else {
      // Clear errors if validation succeeds
      matchingControl.setErrors(null);
    }

    return null;
  };
}
