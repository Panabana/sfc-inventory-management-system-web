<nav class="sidebar">
  <ul class="sidebar-menu">
    <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=home" class="sidebar-menu-link ${activePage == 'home' ? 'active' : ''}">
        <i class="fas fa-home"></i>Home</a>
    <%-- </li>
    <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=product" class="sidebar-menu-link ${activePage == 'product' ? 'active' : ''}">
        <i class="fas fa-box"></i>Products</a>
    </li> --%>
    <%-- <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=supplier" class="sidebar-menu-link ${activePage == 'supplier' ? 'active' : ''}">
        <i class="fas fa-truck"></i>Suppliers</a>
    </li> --%>
    <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=purchase" class="sidebar-menu-link ${activePage == 'purchase' ? 'active' : ''}">
        <i class="fas fa-shopping-cart"></i>Purchases</a>
    </li>
    <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=customer" class="sidebar-menu-link ${activePage == 'customer' ? 'active' : ''}">
        <i class="fas fa-user"></i>Customers</a>
    </li>
    <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=employee" class="sidebar-menu-link ${activePage == 'employee' ? 'active' : ''}">
        <i class="fas fa-address-card"></i>Employees</a>
    </li>
    <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=about" class="sidebar-menu-link ${activePage == 'about' ? 'active' : ''}">
        <i class="fas fa-users"></i>About us</a>
    </li>
    <%-- <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=test" class="sidebar-menu-link ${activePage == 'test' ? 'active' : ''}">
        <i class="fas fa-microscope"></i>Test</a>
    </li> --%>
  </ul>
</nav>