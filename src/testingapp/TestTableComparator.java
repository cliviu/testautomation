package testingapp;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

// Copyright (c) 2011, By ZNT GmbH.  All Rights Reserved.
//*********************************************************************
//
//                     ZNT GmbH
//                     Mautnerstrasse 268
//                     84489 Burghausen
//                     GERMANY
//                     +49 (8677) 9880-0
//
// This software is furnished under a license and may be used
// and copied only in accordance with the terms of such license.
// This software or any other copies thereof in any form, may not be
// provided or otherwise made available, to any other person or company
// without written consent from ZNT.
//
// ZNT GmbH assumes no responsibility for the use or reliability of
// software which has been modified without approval.
//*********************************************************************
// Description:
// Author:    carausu

public class TestTableComparator extends ViewerComparator
{
    private int propertyIndex;
    private static final int DESCENDING = 1;
    private int direction = DESCENDING;

    public TestTableComparator() {
        this.propertyIndex = 0;
        direction = DESCENDING;
    }

    public void setColumn(int column) {
        if (column == this.propertyIndex) {
            // Same column as last sort; toggle the direction
            direction = 1 - direction;
        } else {
            // New column; do an ascending sort
            this.propertyIndex = column;
            direction = DESCENDING;
        }
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2)
    {
    	//System.out.println("Compare" + ((Test)e1).getName() + "with "   + ((Test)e2).getName());
        int rc = 0;
        switch (propertyIndex)
        {
            case 0:
                rc = ((Test)e1).getName().compareTo(((Test)e2).getName());
                break;
            default:
                rc = 0;
        }
        // If descending order, flip the direction
        if (direction == DESCENDING) {
            rc = -rc;
        }
        return rc;
    }

    public int getDirection()
    {
        return direction == 1 ? SWT.DOWN : SWT.UP;
    }


}
