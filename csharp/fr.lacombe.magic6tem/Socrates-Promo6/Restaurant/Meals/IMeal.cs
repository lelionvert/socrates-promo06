﻿using System.Collections.Generic;
using System.Text;

namespace Socrates_Promo6
{
    public interface IMeal
    {
        Covers From(IEnumerable<Participant> participants);
    }
}
