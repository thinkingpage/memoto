import {MemoModel} from '../../models/memo.model';
import {Component, input, signal} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

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
