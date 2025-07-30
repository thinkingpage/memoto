import {Component, input, output, signal} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
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
    title: new FormControl(""),
    content: new FormControl("")
  })

  formMemo() {

    if(this.addMemoForm.invalid) {
      this.addMemoForm.markAllAsTouched();
      this.userInputMessage.set("Need Title and Content!");
      return;
    }

    let title: string = this.addMemoForm.get("title")?.value ?? "";
    let content: string = this.addMemoForm.get("content")?.value ?? ""

    if(title.length == 0 && content.length == 0) {
      this.userInputMessage.set("Need Title and Content!");
      console.log("Need Title and Content!")
      console.log("\n" + this.userInputMessage)
      return;
    }
    const memo: MemoModel = {
      title: title,
      content: content
    };

    this.emitForm.emit(memo);
  }
}
