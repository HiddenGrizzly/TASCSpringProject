import { FormGroup } from "@angular/forms";

export function matchPassword(inputField: string, matchingInputField: string) {
    return (formGroup: FormGroup) => {
        let input = formGroup.controls[inputField];
        let matchingInput = formGroup.controls[matchingInputField];
        if (matchingInput.errors && !matchingInput.errors['matchPassword']) {
            return;
        }
        if (input.value !== matchingInput.value) {
            return matchingInput.setErrors({ unmatch: true });
        } else {
            return matchingInput.setErrors(null);
        }
    }
}
