import {MemoModel} from '../../models/memo.model';
import {Component, input, signal} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-memo-item',
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: 'memo-item.component.html',
})
export class MemoItemComponent {
  showMemoById = input<boolean>();
  memoFromBackend = input<MemoModel | null>();
}
