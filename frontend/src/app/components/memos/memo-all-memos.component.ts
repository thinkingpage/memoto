import {MemoModel} from '../../models/memo.model';
import {Component, input, output, signal, WritableSignal} from '@angular/core';
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
  emitMemoId = output<number>();
  emitMemoIdToDelete = output<number>();


  // TODO: fix this!
  // Beide Methoden benötigen eine ID. vielleicht kann ich ja auch etwas mitgeben wie "id", "operation"?
  // Wäre das der sinvollere Weg?
  mbid(id: number | undefined): void {
    if (id != null) {
      this.emitMemoId.emit(id);
    }
  }

  deleteMemoById(id: number | undefined): void {
    if (id != null) {
      this.emitMemoIdToDelete.emit(id);
    }
  }

}
