import {Component, effect, inject, output, signal} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MemoModel} from '../../models/memo.model';
import {MemoStateService} from './memo-state.service';

@Component({
  selector: 'app-memo-add-memo',
    imports: [
        FormsModule,
        ReactiveFormsModule
    ],
  templateUrl: './memo-add-memo.html',
})
export class MemoAddMemo {

  userInputMessage = signal<string | null>(null);
  private memoStateService: MemoStateService = inject(MemoStateService)

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

    this.userInputMessage.set(null)

    let memo: MemoModel = {
      title: this.title,
      content: this.content
    };

    this.memoStateService.addMemoFormInput.set(memo);
    this.memoStateService.addMemo();
    this.addMemoForm.reset();
  }
}
