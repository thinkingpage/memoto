import {MemoModel} from '../../models/memo.model';
import {HttpErrorResponse} from '@angular/common/http';
import {Component, input, signal} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MemoService} from '../../memo.service';

@Component({
  selector: 'app-all-memos',
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: 'memo-all-memos.component.html',
})
export class MemoAllMemosComponent {
  showAllMemos = input<boolean>();
  memosFromDB = input<MemoModel[]>();
}
