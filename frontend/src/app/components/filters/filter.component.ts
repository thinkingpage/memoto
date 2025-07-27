import {Component, signal} from '@angular/core';
import {MemoService} from '../../memo.service';
import {MemoModel} from '../../models/memo.model';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-filter',
  imports: [],
  templateUrl: './filter.component.html',
})
export class FilterComponent {

  constructor(private memoService: MemoService) {}
  public memos = signal<MemoModel[]>([]);
  public showAddFormBool = signal(false);
  public showAllMemosBool = signal(false);

  showAddForm() {
    this.showAddFormBool.update(value => !value);
  }

  addMemo() {

  }

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

