import {UserProfileService} from '../../keycloak/user-profile.service';
import {Component, inject, input, output} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MemoDTO} from '../../models/memo.dto.model';

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
  memosFromDB = input<MemoDTO[]>();
  emitMemoId = output<number>();
  emitMemoIdToDelete = output<number>();

  protected readonly userProfileService = inject(UserProfileService);

  // goal: save custom color variants in database.
  // colorVariants should have a font color too

  private colorVariants = [
    'bg-[#AF964C]',
    'bg-[#BB917B]',
    'bg-[#cab988]'
  ]

  backgroundColorGenerator(id: number | undefined) {
    if(id) {
      return this.colorVariants[(id % this.colorVariants.length)];
    } return;
  }

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
