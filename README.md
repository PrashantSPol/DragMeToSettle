# DragMeToSettle
Android library to create draggable layouts which can be settled at pre-defined location.

Different draggable layouts can be set and it can be linked with corresponding resting positions,
so that it can be dragged and auto settled at correct position.

Best way to provide questionair or pair matching user interfaces.


    // in layout file
    <com.polstech.library.dragme.library.DragLayout>
        // Resting Position 1
        <com.polstech.library.dragme.library.RestElement>
            <child views>
        </com.polstech.library.dragme.library.RestElement>
        
        // Draggable Element
        <com.polstech.library.dragme.library.DragElement>
            <child views>
        </com.polstech.library.dragme.library.DragElement>
    </com.polstech.library.dragme.library.DragLayout>
        
    // In Activity or fragment
    dragElement.setRestingLayout(restElement);
