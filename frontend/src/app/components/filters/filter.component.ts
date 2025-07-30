import {Component, input, output, signal} from '@angular/core';
import {MemoService} from '../../memo.service';
import {MemoModel} from '../../models/memo.model';
import {HttpErrorResponse} from '@angular/common/http';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-filter',
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './filter.component.html',
})
export class FilterComponent {

  showAddForm = signal<boolean>(false);
  emitForm = output<MemoModel>();
  triggerAllMemoLoad = output<>();

  constructor(private memoService: MemoService) {
  }

  addMemoForm = new FormGroup({
    title: new FormControl(""),
    content: new FormControl("")
  })

  getAllMemos(): void {
    this.triggerAllMemoLoad.emit();
  }

  activateAddForm() {
    this.showAddForm.update(value => !value);
  }

  addMemo() {
    console.log(
      "title: " + this.addMemoForm.get("title")?.value +
      "\ncontent: " + this.addMemoForm.get("content")?.value
    );

    const memo: MemoModel = {
      title: this.addMemoForm.get("title")?.value ?? "",
      content: this.addMemoForm.get("content")?.value ?? ""
    };

    // formgroup = output(); und emit.

    this.emitForm.emit(memo);
  }
}

