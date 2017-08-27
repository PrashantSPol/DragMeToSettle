# DragMeToSettle
Android library to create draggable layouts which can be settled at pre-defined location.


    // in layout file
    <com.polstech.library.dragme.library.DragLayout
    ....... >
        // Resting Position 1
        <com.polstech.library.dragme.library.RestElement
          .......>
            <other elements>
        </com.polstech.library.dragme.library.RestElement>
        
        // Draggable Element
        <com.polstech.library.dragme.library.DragElement
              ........>
        </com.polstech.library.dragme.library.DragElement>
    </com.polstech.library.dragme.library.DragLayout>
        
    // In Activity or fragment
    dragElement.setRestingLayout(restElement);
