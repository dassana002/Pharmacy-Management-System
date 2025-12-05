# DashBoard.css - Quick Reference Guide

## Available Style Classes

### Layout Classes
- `.root` - Main container
- `.dashboard-container` - Dashboard wrapper
- `.main-dashboard-container` - Main container with white background
- `.dashboard-vbox` - Main vertical box
- `.center-content` - Center content area background

### Header/Navigation
- `.header-section` - Top header with gradient background
- `.dashboard-title` - Main title text
- `.dashboard-subtitle` - Subtitle text
- `.dashboard-logo` - Logo container
- `.logo-circle` - Logo circle shape
- `.logo-text` - Logo text (Rx)
- `.user-info` - User name label
- `.user-role` - User role label
- `.logout-button` - Logout button (red)
- `.divider` - Horizontal divider line

### Sidebar
- `.sidebar-vbox` - Sidebar container (dark blue-gray)
- `.sidebar-header` - Sidebar header section
- `.dashboard-title-label` - "DashBoard" label in sidebar
- `.sidebar-button` - Navigation buttons in sidebar

### Buttons
- `.btn-primary` - Primary action button (teal)
- `.btn-secondary` - Secondary action button (gray)
- `.btn-danger` - Danger/delete button (red)
- `.btn-success` - Success/confirm button (green)

### Form Controls
- `.text-field` - Text input field
- `.text-area` - Text area input
- `.combo-box` - Dropdown selector
- `.check-box` - Checkbox control
- `.radio-button` - Radio button control

### Tables & Lists
- `.table-view` - Table container
- `.table-view .column-header` - Table column headers
- `.table-row-cell` - Table rows

### Text
- `.label` - Standard label
- `.label-dark` - Dark/inverted label
- `.label-header` - Header-style label

### Other Elements
- `.scroll-bar` - Scrollbar styling
- `.separator` - Separator line
- `.context-menu` - Context menu
- `.menu-item` - Menu item
- `.tooltip` - Tooltip

## Color Reference

| Element | Color | Hex Code |
|---------|-------|----------|
| Primary Text | Dark Blue | #2c3e50 |
| Sidebar Background | Slate | #34495e |
| Headers | Dark Blue | #2c3e50 |
| Accent/Hover | Teal | #16a085 |
| Background | Light Gray | #f5f5f5 |
| Content | Off-White | #ffffff |
| Borders | Border Gray | #bdc3c7 |
| Light Text | Light Gray | #ecf0f1 |
| Danger/Error | Red | #e74c3c |
| Success | Green | #27ae60 |

## Usage Examples

### In FXML - Applying Style Classes
```xml
<!-- Primary button -->
<Button text="Save" styleClass="btn-primary" />

<!-- Secondary button -->
<Button text="Cancel" styleClass="btn-secondary" />

<!-- Danger button -->
<Button text="Delete" styleClass="btn-danger" />

<!-- Success button -->
<Button text="Confirm" styleClass="btn-success" />

<!-- Text field -->
<TextField styleClass="text-field" />

<!-- Table view -->
<TableView styleClass="table-view">
    <!-- Table definition -->
</TableView>

<!-- Label -->
<Label text="Title" styleClass="label-header" />
```

### Multiple Classes
```xml
<Label text="Important" styleClass="label label-dark" />
```

## Responsive Behavior

- `.sidebar-button` - Wraps text and centers it
- `.table-row-cell:hover` - Changes background on hover
- `.text-field:focused` - Changes border color when focused
- `.combo-box-popup .list-cell:filled:selected` - Highlights selected items

## State Selectors

- `:hover` - When mouse hovers over element
- `:pressed` - When button is pressed
- `:focused` - When element has keyboard focus
- `:selected` - When item is selected
- `:filled` - When value is present

## Customization Tips

1. To change all button colors:
   - Modify `.btn-primary`, `.btn-secondary`, etc.

2. To change text field styling:
   - Modify `.text-field` and `.text-field:focused`

3. To change table appearance:
   - Modify `.table-view` and `.table-row-cell`

4. To change header styling:
   - Modify `.header-section` and `.dashboard-title`

5. To add rounded corners:
   - Use `-fx-border-radius: X;` where X is the radius

6. To add shadows:
   - Use `-fx-effect: dropshadow(gaussian, rgba(...), ...);`
