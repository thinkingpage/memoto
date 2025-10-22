import {effect, inject, Injectable, signal} from '@angular/core';
import {MemoDTO} from '../../models/memo.dto.model';
import {HttpErrorResponse} from '@angular/common/http';
import {MemoService} from '../../memo.service';
import {MemoModel} from '../../models/memo.model';

@Injectable({ providedIn: 'root' })
export class MemoStateService {
  memos = signal<MemoDTO[]>([]);
  memoToDelete = signal<number>(0);
  usernameFromClicked = signal<string>("")
  addMemoFormInput = signal<MemoModel>({
    title: '',
    content: '',
  });


  private memoService: MemoService = inject(MemoService)

  private _loadAllMemosFromUserName = effect(() => {
    if (this.usernameFromClicked() && this.memos()) {
      this.memoService.getMemosByUser(this.usernameFromClicked()).subscribe({
        error: (error: HttpErrorResponse) => {
          console.log("errormessage: " + error.message);
        },
        next: (response: MemoDTO[])=> {
          this.memos.set(response);
        }
      })
    }
  });

  private _addMemo = effect(() => {
    let memo = this.addMemoFormInput();
    if(memo) {
      this.memoService.addMemo(memo).subscribe({
          next: (response: MemoDTO) => {
            this.memos.update(value => {
              return [...value, response];
            });
          },
          error: (error: HttpErrorResponse) => {
            console.log("errormessage: " + error.message);
          },
        }
      )
    }})

  private _deleteMemoFromId = effect(() => {
    if(this.memoToDelete()) {
      this.memoService.deleteMemo(this.memoToDelete()).subscribe({
        next: () => {
          console.log("send.")
          this.memos.update(value => {
              return value.filter(memo => memo.id !== this.memoToDelete());
            }
          )
        },
        error: (error: HttpErrorResponse) => {
          console.log("errormessage: " + error.message);
          return error;
        },
      });
    }
  })
}
