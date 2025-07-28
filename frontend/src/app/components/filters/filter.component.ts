import {Component, input, signal} from '@angular/core';
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

  constructor(private memoService: MemoService) {
  }

  memos = signal<MemoModel[]>([]);
  showAddFormBool = signal(false);
  showAllMemosBool = signal(false);

  addMemoForm = new FormGroup({
    title: new FormControl(""),
    content: new FormControl("")
  })

  getAllMemos() {
    this.showAllMemosBool = input();
  }


  showAddForm() {
    this.showAddFormBool.update(value => !value);
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

    this.memoService.addMemo(memo).subscribe({
        next: (response: MemoModel) => {
          console.log(response);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error.message);
        },
      }
    )
  }
}

