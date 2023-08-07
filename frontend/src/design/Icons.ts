import { ReactComponent as Plus } from '../asset/icons/plus.svg';
import { ReactComponent as ReFresh } from '../asset/icons/refresh.svg';
import { ReactComponent as AlertCircle } from '../asset/icons/alert_circle.svg';
import { ReactComponent as ChevronDown } from '../asset/icons/chevron_down.svg';
import { ReactComponent as UserImageSmall } from '../asset/icons/user_image_small.svg';
import { ReactComponent as userImageLarge } from '../asset/icons/userImageLarge.svg';
import { ReactComponent as CheckOnCircle } from '../asset/icons/check_on_circle.svg';
import { ReactComponent as CheckOffCircle } from '../asset/icons/check_off_circle.svg';
import { ReactComponent as PaperClip } from '../asset/icons/paperclip.svg';
import { ReactComponent as Edit } from '../asset/icons/edit.svg';
import { ReactComponent as Smile } from '../asset/icons/smile.svg';
import { ReactComponent as Search } from '../asset/icons/search.svg';
import { ReactComponent as CheckBoxInitial } from '../asset/icons/check_box_initial.svg';
import { ReactComponent as CheckBoxDisable } from '../asset/icons/check_box_disable.svg';
import { ReactComponent as CheckBoxActive } from '../asset/icons/check_box_active.svg';
import { ReactComponent as Archive } from '../asset/icons/archive.svg';
import CheckBox from '../constant/CheckBox';

const { initial, disable, active } = CheckBox;

const Icons = {
  plus: Plus,
  refresh: ReFresh,
  alertCircle: AlertCircle,
  archive: Archive,
  chevronDown: ChevronDown,
  userImageSmall: UserImageSmall,
  userImageLarge: userImageLarge,
  checkOnCircle: CheckOnCircle,
  checkOffCircle: CheckOffCircle,
  paperClip: PaperClip,
  edit: Edit,
  smile: Smile,
  search: Search,
  default: Plus,
  checkbox: {
    [initial]: CheckBoxInitial,
    [disable]: CheckBoxDisable,
    [active]: CheckBoxActive,
  },
} as const;

export default Icons;
