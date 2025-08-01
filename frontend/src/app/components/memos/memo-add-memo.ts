import {Component, input, output, signal} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MemoModel} from '../../models/memo.model';

@Component({
  selector: 'app-memo-add-memo',
    imports: [
        FormsModule,
        ReactiveFormsModule
    ],
  templateUrl: './memo-add-memo.html',
})
export class MemoAddMemo {

  showAddMemoForm = input<boolean>()
  emitForm = output<MemoModel>();
  userInputMessage = signal<string>("");

  addMemoForm = new FormGroup({
    title: new FormControl("", [Validators.required, Validators.minLength(1)]),
    content: new FormControl("", [Validators.required, Validators.minLength(3)])
  })

  get title() {
    return this.addMemoForm.get("title")?.value ?? "";
  }

  get content() {
    return this.addMemoForm.get("content")?.value ?? "";
  }

  formMemo() {
    if(this.addMemoForm.invalid) {
      this.userInputMessage.set("Need Title and Content!");
      return;
    }

    if(this.title.length == 0 && this.content.length == 0) {
      this.userInputMessage.set("Need Title and Content!");
      return;
    }

    let memo: MemoModel = {
      title: this.title,
      content: this.content
    };

    this.emitForm.emit(memo);
    this.addMemoForm.reset();
  }
}
