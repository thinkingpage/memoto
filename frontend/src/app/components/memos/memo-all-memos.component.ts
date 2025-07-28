import {MemoModel} from '../../models/memo.model';
import {HttpErrorResponse} from '@angular/common/http';
import {Component, signal} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MemoService} from '../../memo.service';

@Component({
  selector: 'app-filter',
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './filter.component.html',
})
export class MemoAllMemosComponent {

  constructor(private memoService: MemoService) {
  }

  memos = signal<MemoModel[]>([]);
  showAllMemosBool = signal(false);

  getAllMemos() {
    this.showAllMemosBool.update(value => !value);
    this.memoService.getMemos().subscribe({
      next: (response: MemoModel[]) => {
        this.memos.set(response);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    });
  }
}
